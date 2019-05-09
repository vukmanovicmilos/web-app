import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort, MatDialog, PageEvent } from '@angular/material';
import { Student } from '../../models/student';
import { StudentService } from '../../services/student.service';
import { StudentDialogComponent } from '../dialogs/student-dialog/student-dialog.component';
import { Course } from '../../models/Course';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrls: ['./student.component.css']
})
export class StudentComponent implements OnInit {


  displayedColumns = ['id', 'firstName', 'lastName', 'indexNumber', 'actions'];
  dataSource: MatTableDataSource<Student>;
  filter = "*";

  @Input() selectedCourse: Course;
  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(public studentService: StudentService, public dialog: MatDialog) { }

  ngOnInit() {
    this.loadData(this.paginator.pageIndex, this.paginator.pageSize, "*");
    this.paginator.pageSize = 10
  }

  ngOnChanges() {
    if (this.selectedCourse.id) {
      this.paginator.pageIndex = 0;
      this.loadData(this.paginator.pageIndex, this.paginator.pageSize, this.selectedCourse.id);
      this.displayedColumns = ['id', 'firstName', 'lastName', 'indexNumber'];
    }
  }

  public loadData(pageIndex, pageSize, filter) {
    if (this.selectedCourse) {
      this.studentService.getStudentsForCourse(pageIndex, pageSize, this.selectedCourse.id).subscribe(data => {
        this.dataSource = new MatTableDataSource(data['content']);
        this.paginator.length = data['totalElements'];
        this.dataSource.sort = this.sort;
      });
    }
    else {
      this.studentService.getAllStudents(pageIndex, pageSize, filter).subscribe(data => {
        this.dataSource = new MatTableDataSource(data['content']);
        this.paginator.length = data['totalElements'];
        this.dataSource.sort = this.sort;
      });
    }
  }

  getNext(event: PageEvent) {
    this.loadData(event.pageIndex, event.pageSize, this.filter);
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    if (filterValue.length == 0) // kada se polje za pretragu obriše učitaj sve
      filterValue = "*";

    this.studentService.getAllStudents(this.paginator.pageIndex, this.paginator.pageSize, filterValue).subscribe(data => {
      this.dataSource = new MatTableDataSource(data['content']);
      this.paginator.length = data['totalElements']
    });
    this.filter = filterValue;
    this.paginator.pageIndex = 0;
  }

  public openDialog(flag: number, id: number, firstName: string, lastName: string, indexNumber: string) {
    const dialogRef = this.dialog.open(StudentDialogComponent, { data: { id: id, firstName: firstName, lastName: lastName, indexNumber: indexNumber } });
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(result => {
      if (result == 1 && flag == 1)
        this.loadData(this.paginator.getNumberOfPages() - 1, this.paginator.pageSize, "*");
      else
        this.loadData(this.paginator.pageIndex, this.paginator.pageSize, "*");
    });
  }
}