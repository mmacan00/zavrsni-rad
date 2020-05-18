import React from "react";
import {getAttributes} from "../services/FeatureAvailabilityRuleService";
import {Button, FormControl, Grid, InputLabel, MenuItem, Paper, Select, TextField} from "@material-ui/core";
import styles from "./Styles.module.css";
import {GroupCondition, OrOperationModel} from "../types/external";

interface Props {
    orOperations: OrOperationModel[];

    handleOrButtonClick(): void;

    handleAndButtonClick(orIndex: number): void;

    handleAttributeChange(event: React.ChangeEvent<{ name?: string, value: unknown }>, orIndex: number, andIndex: number): void;

    handleConditionChange(event: React.ChangeEvent<{ name?: string, value: unknown }>, orIndex: number, andIndex: number): void;

    handleCriteriaChange(e: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>, orIndex: number, andIndex: number): void;
}

interface State {
    attributes: string[];
}

export class FeatureRuleForm extends React.Component<Props, State> {

    async componentDidMount() {
        const attributes = await getAttributes();
        this.setState({attributes});
    }

    render() {
        return (
            <Paper variant="outlined" className={styles.margin_padding}>
                <Grid container>
                    {this.renderRule()}
                </Grid>
            </Paper>
        );
    }

    private renderRule = () => {
        let orOperationContainers = [] as JSX.Element[];

        for (const [orIndex, orOperation] of this.props.orOperations.entries()) {

            let andOperationContainers = this.extracted(orOperation, orIndex);

            orOperationContainers = [
                ...orOperationContainers,
                <Paper key={orIndex} variant="outlined" className={styles.margin_padding}>
                    <Grid container>
                        {andOperationContainers}
                        <Button variant="contained"
                                onClick={this.props.handleOrButtonClick}>
                            + OR
                        </Button>
                    </Grid>
                </Paper>
            ]
        }
        return orOperationContainers;
    }

    private extracted = (orOperation: OrOperationModel, orIndex: number) => {
        let andOperationContainers = [] as JSX.Element[];

        for (const [andIndex, andOperation] of orOperation.andOperations.entries()) {

            andOperationContainers = [
                ...andOperationContainers,
                <Paper key={andIndex} variant="outlined" className={styles.margin_padding}>
                    <Grid container>
                        {this.renderAttributeSelect(andOperation.groupStrategy, orIndex, andIndex)}
                        {this.renderConditionSelect(andOperation.condition, orIndex, andIndex)}
                        {this.renderCriteriaInput(andOperation.criteria, orIndex, andIndex)}
                        <Button variant="contained"
                                onClick={() => this.props.handleAndButtonClick(orIndex)}>
                            + AND
                        </Button>
                    </Grid>
                </Paper>
            ]
        }
        return andOperationContainers;
    }

    private renderAttributeSelect = (attribute: string, orIndex: number, andIndex: number) => {
        return (
            <Grid item xs={4}>
                <FormControl className={styles.min_input_width}>
                    <InputLabel id="attribute-label">Attribute</InputLabel>
                    <Select className={styles.min_input_width} labelId="attribute-label"
                            id="attribute-select" value={attribute}
                            onChange={e => this.props.handleAttributeChange(e, orIndex, andIndex)}>
                        {this.renderAttributeItems()}
                    </Select>
                </FormControl>
            </Grid>
        );
    };

    private renderAttributeItems = () => {
        return (
            (this.state?.attributes || []).map(attribute =>
                <MenuItem key={attribute} value={attribute}>{attribute}</MenuItem>)
        );
    }

    private renderConditionSelect = (condition: GroupCondition, orIndex: number, andIndex: number) => {
        return (
            <Grid item xs={4}>
                <FormControl className={styles.min_input_width}>
                    <InputLabel id="condition-label">Condition</InputLabel>
                    <Select className={styles.min_input_width} labelId="condition-label"
                            id="condition-select" value={condition}
                            onChange={e => this.props.handleConditionChange(e, orIndex, andIndex)}>
                        {this.renderConditionItems()}
                    </Select>
                </FormControl>
            </Grid>
        );
    };

    private renderConditionItems = () => {
        return [GroupCondition.IS, GroupCondition.IS_NOT]
            .map(condition => <MenuItem key={condition} value={condition}>{condition}</MenuItem>);
    }

    private renderCriteriaInput = (criteria: string, orIndex: number, andIndex: number) => {
        return (
            <Grid item xs={4}>
                <TextField id="criteria" label="criteria" value={criteria}
                           onChange={e => this.props.handleCriteriaChange(e, orIndex, andIndex)}/>
            </Grid>
        );
    };
}
