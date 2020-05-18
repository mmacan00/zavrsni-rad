import React from "react";
import {Button, Container, Grid, Paper, TextField} from "@material-ui/core";
import styles from "./Styles.module.css";
import {isUserAllowed} from "../services/FeatureAvailabilityRuleService";

interface State {
    userId: string;
    namespace: string;
    feature: string;
    allowed?: string;
}

export class Demo extends React.Component<{}, State>{

    state = {
        userId: '',
        namespace: '',
        feature: ''
    } as State

    render() {
        return (
            <Container maxWidth="lg">
                <Paper elevation={3} className={styles.margin_padding}>
                    <Grid container justify="center">
                        {this.renderNamespaceInput()}
                        {this.renderFeatureInput()}
                        {this.renderUserIdInput()}
                        <Button onClick={this.handleCheckButtonClick} variant="contained">CHECK</Button>
                        <h2>{this.state.allowed}</h2>
                    </Grid>
                </Paper>
            </Container>
        )
    }

    private renderUserIdInput = () => {
        return (
            <Grid item xs={3}>
                <TextField id="userId" label="userId" value={this.state.userId} onChange={this.handleUserIdChange}/>
            </Grid>
        );
    };

    private handleUserIdChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        this.setState({
            userId: e.target.value
        })
    };

    private renderNamespaceInput = () => {
        return (
            <Grid item xs={3}>
                <TextField id="namespace" label="namespace" value={this.state.namespace} onChange={this.handleNamespaceChange}/>
            </Grid>
        );
    };

    private handleNamespaceChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        this.setState({
            namespace: e.target.value
        })
    };

    private renderFeatureInput = () => {
        return (
            <Grid item xs={3}>
                <TextField id="feature" label="feature" value={this.state.feature} onChange={this.handleFeatureChange}/>
            </Grid>
        );
    };

    private handleFeatureChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        this.setState({
            feature: e.target.value
        })
    };

    private handleCheckButtonClick = async () => {
        const response = await isUserAllowed(this.state.namespace, this.state.feature, this.state.userId);
        this.setState({
            allowed: response
        })
    };
}
