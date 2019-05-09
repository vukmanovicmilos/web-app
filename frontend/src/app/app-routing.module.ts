import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CourseComponent } from './components/course/course.component';
import { TeacherComponent } from './components/teacher/teacher.component';
import { StudentComponent } from './components/student/student.component';
import { AuthorComponent } from './components/core/author/author.component';
import { HelpComponent } from './components/core/help/help.component';
import { HomeComponent } from './components/core/home/home.component';

const routes: Routes = [
  { path: 'course', component: CourseComponent},
  { path: 'teacher', component: TeacherComponent},
  { path: 'student', component: StudentComponent},
  { path: 'author', component: AuthorComponent},
  { path: 'help', component: HelpComponent},
  { path: 'home', component: HomeComponent, data: {animation: 'HomePage'}},
  { path: '',   redirectTo: 'home', pathMatch: 'full' },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
