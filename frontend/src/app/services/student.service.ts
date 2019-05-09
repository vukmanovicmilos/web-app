
import { Injectable } from "@angular/core";
import { HttpClient, HttpErrorResponse, HttpHeaders } from "@angular/common/http";
import { BehaviorSubject, Observable } from 'rxjs';
import { Student } from '../models/student';

@Injectable()
export class StudentService {

    private readonly API_URL = 'http://localhost:8765/faculty/student/';

    constructor(private httpClient: HttpClient) {

    }
    public getStudentsForCourse(page: number, size: number, courseId: number): Observable<Student[]> {
        console.log(this.API_URL + 'studentsForCourse/' + courseId + '?page=' + page + '&size=' + size)
        return this.httpClient.get<Student[]>(this.API_URL + 'studentsForCourse/' + courseId + '?page=' + page + '&size=' + size);
    }

    public getAllStudents(page: number, size: number, filter: string): Observable<Student[]> {
        console.log(this.API_URL + filter + '?page=' + page + '&size=' + size)
        return this.httpClient.get<Student[]>(this.API_URL + filter + '?page=' + page + '&size=' + size);
    }

    public addStudent(student: Student): void {
        this.httpClient.post(this.API_URL, student).subscribe();
    }

    public updateStudent(student: Student): void {
        this.httpClient.put(this.API_URL, student).subscribe();
    }

    public deleteStudent(id: number): void {
        this.httpClient.delete(this.API_URL + id).subscribe();
    }
}