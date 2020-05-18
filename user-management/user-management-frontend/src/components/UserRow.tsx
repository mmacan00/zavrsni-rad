import {deleteUser, saveUser, updateUser} from "../services/UserService";
import {Button, TableCell, TableRow, TextField} from "@material-ui/core";
import React from "react";
import {User} from "../types/external";
import styles from "./Styles.module.css";

interface Props {
    user: User;

    updateStateOnChange(): void;
    handleFirstNameChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleLastNameChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleUserNameChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleEmailChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleCityChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleCountryChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleGenderChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleWeightKgChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleHeightCmChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
    handleDateOfBirthChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string): void;
}

export const UserRow = (props: Props) => {

    const handleDelete = async () => {
        await deleteUser(props.user.id);
        props.updateStateOnChange();
    };

    const handleSave = async () => {
        if(props.user.id) {
            await updateUser(props.user);
        }else {
            await saveUser(props.user);
        }
        props.updateStateOnChange();
    };

    return (
        <TableRow>
            <TableCell className={styles.maxWidth}>{props.user.id}</TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="firstName" label="First Name" value={props.user.firstName}
                           onChange={(e) => props.handleFirstNameChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="lastName" label="Last Name" value={props.user.lastName}
                           onChange={(e) => props.handleLastNameChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="userName" label="User Name" value={props.user.userName}
                           onChange={(e) => props.handleUserNameChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="email" label="Email" value={props.user.email}
                           onChange={(e) => props.handleEmailChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="city" label="City" value={props.user.city}
                           onChange={(e) => props.handleCityChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="country" label="Country" value={props.user.country}
                           onChange={(e) => props.handleCountryChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="gender" label="Gender" value={props.user.gender}
                           onChange={(e) => props.handleGenderChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="weightKg" label="Weight Kg" value={props.user.weightKg}
                           onChange={(e) => props.handleWeightKgChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="heightCm" label="Height Cm" value={props.user.heightCm}
                           onChange={(e) => props.handleHeightCmChange(e, props.user.id)}/>
            </TableCell>
            <TableCell className={styles.maxWidth}>
                <TextField id="dob" label="Date of Birth" value={props.user.dateOfBirth}
                           onChange={(e) => props.handleDateOfBirthChange(e, props.user.id)}/>
            </TableCell>
            <TableCell>
                <Button onClick={handleSave} variant={"contained"}>Save</Button>
                <Button onClick={handleDelete} variant={"contained"}>Delete</Button>
            </TableCell>
        </TableRow>
    );
};
