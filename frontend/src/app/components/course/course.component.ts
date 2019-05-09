import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource, MatDialog, PageEvent } from '@angular/material';
import { Course } from '../../models/Course';
import { CourseService } from '../../services/course.service';
import { Teacher } from '../../models/teacher';
import { trigger, state, style, animate, transition } from '@angular/animations';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-course',
  templateUrl: './course.component.html',
  styleUrls: ['./course.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0', display: 'none' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class CourseComponent implements OnInit {

  displayedColumns = ['id', 'label', 'name', 'startDate'];
  dataSource: MatTableDataSource<Course>;
  filter = "*";
  expandedElement: Teacher | null;
  selectedCourse: Course;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;
  isShown: boolean;

  constructor(public courseService: CourseService, public dialog: MatDialog) { }

  ngOnInit() {
    this.loadData(this.paginator.pageIndex, this.paginator.pageSize, "*");
    this.paginator.pageSize = 10
  }

  public loadData(pageIndex, pageSize, filter) {
    this.courseService.getAllCourses(pageIndex, pageSize, filter).subscribe(data => {
      this.dataSource = new MatTableDataSource(data['content']);
      this.paginator.length = data['totalElements'];
      this.dataSource.sort = this.sort;
    });
  }

  getNext(event: PageEvent) {
    this.loadData(event.pageIndex, event.pageSize, this.filter);
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    if (filterValue.length == 0) // kada se polje za pretragu obriše učitaj sve
      filterValue = "*";

    this.courseService.getAllCourses(this.paginator.pageIndex, this.paginator.pageSize, filterValue).subscribe(data => {
      this.dataSource = new MatTableDataSource(data['content']);
      this.paginator.length = data['totalElements']
    });
    this.filter = filterValue;
    this.paginator.pageIndex = 0;
  }

  public selectRow(row) {
    this.selectedCourse = row;
  }

  public sendRequest() {
    this.isShown = true;
    this.courseService.getPdf(this.selectedCourse.id).subscribe(pdf => {
      let pdfUrl = window.URL.createObjectURL(pdf);
      window.open(pdfUrl);
      this.isShown = false;
    }, (error: HttpErrorResponse) => {
      console.log(error.name + ' ' + error.message)
      this.isShown = false;
    });
  }
}