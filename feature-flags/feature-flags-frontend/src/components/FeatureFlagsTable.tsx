import React from "react";
import {FeatureAvailabilityRuleModel} from "../types/external";
import {getAllFeatureAvailabilityRules} from "../services/FeatureAvailabilityRuleService";
import {Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow} from "@material-ui/core";
import {RuleRow} from "./RuleRow";

interface State {
    rules: FeatureAvailabilityRuleModel[]
}

export class FeatureFlagsTable extends React.Component<{}, State> {

    async componentDidMount() {
        const rules = await getAllFeatureAvailabilityRules();
        this.setState({rules});
    }

    render() {
        return (
            <TableContainer component={Paper}>
                <Table aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Namespace</TableCell>
                            <TableCell>Feature</TableCell>
                            <TableCell>Description</TableCell>
                            <TableCell>Active</TableCell>
                            <TableCell>Id</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {this.mapRows()}
                    </TableBody>
                </Table>
            </TableContainer>
        );
    }

    private mapRows = () => {
        if (this.state) {
            return this.state.rules
                .map(rule => <RuleRow key={rule.id} updateStateOnDelete={this.updateStateOnDelete} {...rule}/>);
        }
        return null;
    };

    private updateStateOnDelete = async () => {
        const rules = await getAllFeatureAvailabilityRules();
        this.setState({rules});
    }
}
