import React from "react";
import {
    deleteNamespace,
    getAllNamespaces,
    saveNamespace,
} from "../services/FeatureAvailabilityRuleService";
import {
    Button,
    Paper,
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    TextField
} from "@material-ui/core";
import styles from "./Styles.module.css";
import {NamespaceRequest} from "../types/external";

interface State {
    namespaces: string[];
}

export class NamespacesForm extends React.Component<{}, State> {

    async componentDidMount() {
        const namespaces = await getAllNamespaces();
        this.setState({namespaces});
    }

    render() {
        return (
            <TableContainer component={Paper}>
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Namespace</TableCell>
                            <TableCell>Action</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.mapRows()}
                    </TableBody>
                </Table>
                <Button onClick={this.handleAddButtonClick} variant={"contained"}>Add</Button>
            </TableContainer>
        );
    }

    private mapRows = () => {
        if (this.state) {
            return this.state.namespaces
                .map(namespace => this.renderRow(namespace));
        }
        return null;
    };

    private renderRow = (namespace: string) => {
        return (
            <TableRow>
                <TableCell className={styles.maxWidth}>
                    <TextField id="namespace" label="Namespace" value={namespace}
                               onChange={(e) => this.handleNamespaceChange(e, namespace)}/>
                </TableCell>
                <TableCell>
                    <Button onClick={() => this.handleSave(namespace)} variant={"contained"}>Save</Button>
                    <Button onClick={() => this.handleDelete(namespace)} variant={"contained"}>Delete</Button>
                </TableCell>
            </TableRow>
        )
    };

    private handleNamespaceChange = async (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, namespace?: string) => {
        const index = this.extractCorrectIndex(namespace);
        if (index !== null) {
            let namespaces = [...this.state.namespaces];
            namespaces[index] = e.target.value;
            this.setState({namespaces});
        }
    };

    private extractCorrectIndex = (namespace?: string): number | null => {
        if (namespace) {
            return this.state.namespaces.findIndex(ns => ns === namespace);
        } else if (this.state.namespaces.length === 0) {
            return 0;
        } else if (!this.state.namespaces[this.state.namespaces.length - 1]) {
            return this.state.namespaces.length - 1;
        }
        return null;
    };

    private handleAddButtonClick = () => {
        if (this.state.namespaces.length === 0 || this.state.namespaces[this.state.namespaces.length - 1]) {
            this.setState({
                namespaces: this.state.namespaces.concat('')
            })
        }
    };

    private handleDelete = async (namespace: string) => {
        await deleteNamespace(namespace);
        const namespaces = await getAllNamespaces();
        this.setState({namespaces});
    };

    private handleSave = async (namespace: string) => {
        await saveNamespace({namespace: namespace} as NamespaceRequest);
        const namespaces = await getAllNamespaces();
        this.setState({namespaces});
    };
}
