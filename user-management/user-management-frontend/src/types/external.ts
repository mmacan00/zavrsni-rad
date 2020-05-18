/* tslint:disable */
/* eslint-disable */
// Generated using typescript-generator version 2.19.577 on 2020-05-18 21:54:41.

export interface User {
    id: string;
    firstName: string;
    lastName: string;
    userName: string;
    email: string;
    dateOfBirth: Date;
    city: string;
    country: string;
    gender: Gender;
    heightCm: number;
    weightKg: number;
}

export enum Gender {
    MALE = "MALE",
    FEMALE = "FEMALE",
}
