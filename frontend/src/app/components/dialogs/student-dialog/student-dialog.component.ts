import { Component, OnInit, Inject } from '@angular/core';
import { Student } from '../../../models/student';
import { MatSnackBar, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { StudentService } from '../../../services/student.service';
import { CourseService } from '../../../services/course.service';
import { Course } from '../../../models/Course';

@Component({
  selector: 'app-student-dialog',
  templateUrl: './student-dialog.component.html',
  styleUrls: ['./student-dialog.component.css']
})
export class StudentDialogComponent implements OnInit {
  students: Student[];
  public flag: number;
  public courses: Course[];
  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<StudentDialogComponent>,
    public studentService: StudentService,
    public courseService: CourseService,
    @Inject(MAT_DIALOG_DATA) public data: Student,

  ) { }

  ngOnInit() {
    this.courseService.getAllCourses(0, 1000, '*').subscribe(courses =>
      this.courses = courses['content']);
  }

  public add(): void {
    this.studentService.addStudent(this.data);
    this.snackBar.open("Student successfully added", "Ok",
      {
        duration: 2500
      });
  }

  public update(): void {
    this.studentService.updateStudent(this.data);
    this.snackBar.open("Student successfully modified", "Ok",
      {
        duration: 2500
      });
  }

  public delete(): void {
    this.studentService.deleteStudent(this.data.id);
    this.snackBar.open("Student successfully deleted", "Ok",
      {
        duration: 2500
      });
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open("Canceled", "Ok",
      {
        duration: 1000
      });
  }

}
