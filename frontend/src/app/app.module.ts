import { ScrollingModule } from '@angular/cdk/scrolling';
import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule, MatButtonToggleModule, MatCheckboxModule, MatDatepickerModule, MatDialogModule, MatExpansionModule, MatGridListModule, MatIconModule, MatInputModule, MatListModule, MatNativeDateModule, MatOptionModule, MatPaginatorModule, MatProgressBarModule, MatProgressSpinnerModule, MatSelectModule, MatSidenavModule, MatSnackBarModule, MatSortModule, MatTableModule, MatToolbarModule, MatTooltipModule } from '@angular/material';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CourseComponent } from './components/course/course.component';
import { StudentDialogComponent } from './components/dialogs/student-dialog/student-dialog.component';
import { StudentComponent } from './components/student/student.component';
import { TeacherComponent } from './components/teacher/teacher.component';
import { StudentService } from './services/student.service';
import { CourseService } from './services/course.service';
import { AuthorComponent } from './components/core/author/author.component';
import { HelpComponent } from './components/core/help/help.component';
import { HomeComponent } from './components/core/home/home.component';
import { PdfViewerModule } from 'ng2-pdf-viewer';

@NgModule({
  declarations: [
    AppComponent,
    StudentComponent,
    TeacherComponent,
    CourseComponent,
    StudentDialogComponent,
    HelpComponent,
    AuthorComponent,
    HomeComponent
  ],
  imports: [
    PdfViewerModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatButtonModule, MatButtonToggleModule, MatIconModule, MatSidenavModule, MatListModule,
    MatGridListModule, MatExpansionModule, MatSortModule, MatTableModule,
    MatToolbarModule, MatSelectModule, MatOptionModule,
    MatSnackBarModule, MatDialogModule, MatInputModule,
    MatCheckboxModule, MatNativeDateModule, MatDatepickerModule, MatPaginatorModule, MatTooltipModule, MatProgressBarModule, MatProgressSpinnerModule,
    FormsModule,
    HttpClientModule,
    ScrollingModule

  ],
  providers: [StudentService, CourseService],
  bootstrap: [AppComponent],
  entryComponents: [StudentDialogComponent]
})
export class AppModule { }
