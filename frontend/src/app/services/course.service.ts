
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from 'rxjs';
import { Course } from '../models/course';

@Injectable()
export class CourseService {

    private readonly API_URL = 'http://localhost:8765/faculty/course/';
    private readonly REPORT_URL = 'http://localhost:8765/report/studentsForCoursePdf/';

    constructor(private httpClient: HttpClient) {

    }
    public getAllCourses(page: number, size: number, filter: string): Observable<Course[]> {
        console.log(this.API_URL + filter + '?page=' + page + '&size=' + size)
        return this.httpClient.get<Course[]>(this.API_URL + filter + '?page=' + page + '&size=' + size);
    }

    public getPdf(courseId) {

        let headers = new HttpHeaders();
        headers = headers.set('Accept', 'application/pdf');
        return this.httpClient.get(this.REPORT_URL + courseId, { headers: headers, responseType: 'blob' });

    }

    public addCourse(course: Course): void {
        this.httpClient.post(this.API_URL, course).subscribe();
    }

    public updateCourse(course: Course): void {
        this.httpClient.put(this.API_URL, course).subscribe();
    }

    public deleteCourse(id: number): void {
        this.httpClient.delete(this.API_URL + id).subscribe();
    }
}