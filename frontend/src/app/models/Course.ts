import { Teacher } from './teacher';

export class Course {
    id: number;
    label: string;
    name: string;
    startDate: string;
    teacher: Teacher;
}