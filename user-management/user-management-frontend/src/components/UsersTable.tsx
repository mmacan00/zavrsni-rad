import {Gender, User} from "../types/external";
import React from "react";
import {getUsers} from "../services/UserService";
import {Button, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";
import {UserRow} from "./UserRow";

interface State {
    users: User[]
}

export class UsersTable extends React.Component<{}, State> {

    async componentDidMount() {
        const users = await getUsers();
        this.setState({users});
    }

    render() {
        return (
            <TableContainer component={Paper}>
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>ID</TableCell>
                            <TableCell>First Name</TableCell>
                            <TableCell>Last Name</TableCell>
                            <TableCell>User Name</TableCell>
                            <TableCell>Email</TableCell>
                            <TableCell>City</TableCell>
                            <TableCell>Country</TableCell>
                            <TableCell>Gender</TableCell>
                            <TableCell>Weight Kg</TableCell>
                            <TableCell>Height Cm</TableCell>
                            <TableCell>Date of Birth</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.mapRows()}
                    </TableBody>
                </Table>
                <Button onClick={this.handleAndButtonClick} variant={"contained"}>Add</Button>
            </TableContainer>
        );
    }

    private mapRows = () => {
        if (this.state) {
            return this.state.users
                .map(user => <UserRow user={user} key={user.id}
                                      handleFirstNameChange={this.handleFirstNameChange}
                                      handleLastNameChange={this.handleLastNameChange}
                                      handleUserNameChange={this.handleUserNameChange}
                                      handleEmailChange={this.handleEmailChange}
                                      handleCityChange={this.handleCityChange}
                                      handleCountryChange={this.handleCountryChange}
                                      handleGenderChange={this.handleGenderChange}
                                      handleWeightKgChange={this.handleWeightKgChange}
                                      handleHeightCmChange={this.handleHeightCmChange}
                                      handleDateOfBirthChange={this.handleDateOfBirthChange}
                                      updateStateOnChange={this.updateStateOnChange}/>);
        }
        return null;
    };

    private updateStateOnChange = async () => {
        const users = await getUsers();
        this.setState({users});
    }

    private handleAndButtonClick = () => {
        if (this.state.users[this.state.users.length - 1].id) {
            this.setState({
                users: this.state.users.concat({
                    id: '',
                    firstName: '',
                    lastName: '',
                    userName: '',
                    email: '',
                    city: '',
                    country: '',
                    gender: Gender.FEMALE,
                    weightKg: 0,
                    heightCm: 0,
                    dateOfBirth: new Date()
                })
            })
        }
    }

    private extractCorrectIndex = (userId?: string): number | null => {
        if (userId) {
            return this.state.users.findIndex(user => user.id === userId);
        } else if (!this.state.users[this.state.users.length - 1].id && this.state.users.length - 1 !== 0) {
            return this.state.users.length - 1;
        }
        return null;
    }

    private handleFirstNameChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].firstName = e.target.value;
            this.setState({users});
        }
    }

    private handleLastNameChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].lastName = e.target.value;
            this.setState({users});
        }
    }

    private handleUserNameChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].userName = e.target.value;
            this.setState({users});
        }
    }

    private handleEmailChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].email = e.target.value;
            this.setState({users});
        }
    }

    private handleCityChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].city = e.target.value;
            this.setState({users});
        }
    }

    private handleCountryChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].country = e.target.value;
            this.setState({users});
        }
    }

    private handleGenderChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            if (e.target.value === Gender.MALE) {
                users[index].gender = Gender.MALE;
                this.setState({users});
            } else if (e.target.value === Gender.FEMALE) {
                users[index].gender = Gender.FEMALE;
                this.setState({users});
            }
        }
    }

    private handleWeightKgChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].weightKg = parseInt(e.target.value);
            this.setState({users});
        }
    }

    private handleHeightCmChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].heightCm = parseInt(e.target.value);
            this.setState({users});
        }
    }

    private handleDateOfBirthChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, userId?: string) => {
        const index = this.extractCorrectIndex(userId);
        if (index !== null) {
            let users = [...this.state.users];
            users[index].dateOfBirth = new Date(e.target.value);
            this.setState({users});
        }
    }
}
