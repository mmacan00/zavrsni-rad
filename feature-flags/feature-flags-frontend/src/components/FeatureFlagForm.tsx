import React from "react";
import {FeatureAvailabilityRuleModel, GroupCondition} from "../types/external";
import {NamespaceSelector} from "./NamespaceSelector";
import {Button, Container, FormControl, Grid, InputLabel, MenuItem, Paper, Select, TextField} from "@material-ui/core";
import styles from "./Styles.module.css";
import {FeatureRuleForm} from "./FeatureRuleForm";
import {
    getFeatureAvailabilityRule,
    saveFeatureAvailabilityRule,
    updateFeatureAvailabilityRule
} from "../services/FeatureAvailabilityRuleService";
import {Link} from "react-router-dom";

interface Props {
    id?: string;
}

export class FeatureFlagForm extends React.Component<Props, FeatureAvailabilityRuleModel> {

    state = {
        namespace: '',
        feature: '',
        description: '',
        active: false,
        orOperations: [
            {
                andOperations: [
                    {
                        groupStrategy: '',
                        condition: GroupCondition.IS,
                        criteria: ''
                    }
                ]
            }
        ]
    } as FeatureAvailabilityRuleModel

    async componentDidMount() {
        if (this.props?.id) {
            const rule = await getFeatureAvailabilityRule(this.props.id);
            this.setState({...rule});
        }
    }

    render() {
        return (
            <Container maxWidth="lg">
                <Paper elevation={3} className={styles.margin_padding}>
                    <Grid container justify="center">
                        {this.renderNamespaceSelect()}
                        {this.renderFeatureInput()}
                        {this.renderDescriptionInput()}
                        {this.renderActiveSelect()}
                        <FeatureRuleForm orOperations={this.state.orOperations}
                                         handleOrButtonClick={this.handleOrButtonClick}
                                         handleAndButtonClick={this.handleAndButtonClick}
                                         handleAttributeChange={this.handleAttributeChange}
                                         handleConditionChange={this.handleConditionChange}
                                         handleCriteriaChange={this.handleCriteriaChange}/>
                    </Grid>
                    <Button variant="contained">
                        <Link to="/" onClick={this.handleSaveButtonClick}>SAVE</Link>
                    </Button>
                </Paper>
            </Container>
        );
    }

    private renderNamespaceSelect = () => {
        return (
            <Grid item xs={3}>
                <NamespaceSelector
                    namespace={this.state.namespace}
                    handleChange={this.handleNamespaceChange}/>
            </Grid>
        );
    };

    private renderFeatureInput = () => {
        return (
            <Grid item xs={3}>
                <TextField id="feature" label="feature"
                           value={this.state.feature}
                           onChange={this.handleFeatureChange}/>
            </Grid>
        );
    };

    private renderDescriptionInput = () => {
        return (
            <Grid item xs={3}>
                <TextField id="description" label="description"
                           value={this.state.description}
                           onChange={this.handleDescriptionChange}/>
            </Grid>
        );
    };

    private renderActiveSelect = () => {
        return (
            <Grid item xs={3}>
                <FormControl className={styles.min_input_width}>
                    <InputLabel id="active-label">Active</InputLabel>
                    <Select className={styles.min_input_width} labelId="active-label"
                            id="active-select" value={this.state.active}
                            onChange={this.handleActiveChange}>
                        {this.renderActiveItems()}
                    </Select>
                </FormControl>
            </Grid>
        );
    };

    private renderActiveItems = () => {
        return [true, false].map((booleanValue, index) =>
            <MenuItem key={index} value={booleanValue ? 'true' : 'false'}>{booleanValue ? 'true' : 'false'}</MenuItem>);
    }

    private handleNamespaceChange = (event: React.ChangeEvent<{ value: string }>): void => {
        this.setState({
            namespace: event.target.value
        });
    };

    private handleFeatureChange = (event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        this.setState({
            feature: event.target.value
        });
    };

    private handleDescriptionChange = (event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
        this.setState({
            description: event.target.value
        });
    };

    private handleActiveChange = (event: React.ChangeEvent<{ name?: string, value: unknown }>): void => {
        if (event.target.value === 'true') {
            this.setState({
                active: true
            });
        } else {
            this.setState({
                active: false
            });
        }
    };

    private handleOrButtonClick = (): void => {
        this.setState({
            orOperations: this.state.orOperations.concat({
                andOperations: [
                    {
                        groupStrategy: '',
                        condition: GroupCondition.IS,
                        criteria: '',
                    }
                ]
            })
        });
    };

    private handleAndButtonClick = (orIndex: number): void => {
        const andOperations = this.state.orOperations[orIndex].andOperations.concat({
            groupStrategy: '',
            condition: GroupCondition.IS,
            criteria: '',
        });

        const orOperations = [...this.state.orOperations];
        orOperations[orIndex].andOperations = [...andOperations];

        this.setState({orOperations});
    };

    private handleAttributeChange = (e: React.ChangeEvent<{ name?: string, value: string }>, orIndex: number, andIndex: number): void => {
        let orOperations = [...this.state.orOperations];
        orOperations[orIndex].andOperations[andIndex].groupStrategy = e.target.value;

        this.setState({orOperations});
    };

    private handleConditionChange = (e: React.ChangeEvent<{ name?: string, value: string }>, orIndex: number, andIndex: number): void => {
        let orOperations = [...this.state.orOperations];

        if (e.target.value === 'IS') {
            orOperations[orIndex].andOperations[andIndex].condition = GroupCondition.IS;
        } else if (e.target.value === 'IS_NOT') {
            orOperations[orIndex].andOperations[andIndex].condition = GroupCondition.IS_NOT
        }

        this.setState({orOperations});
    };

    private handleCriteriaChange = (e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, orIndex: number, andIndex: number): void => {
        let orOperations = [...this.state.orOperations];
        orOperations[orIndex].andOperations[andIndex].criteria = e.target.value;
        this.setState({orOperations});
    };

    private handleSaveButtonClick = async () => {
        if (this.props?.id) {
            await updateFeatureAvailabilityRule(this.state);
        } else {
            await saveFeatureAvailabilityRule(this.state);
        }
    };
}
