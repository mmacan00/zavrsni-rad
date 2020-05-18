import React from "react";
import {Button, TableCell, TableRow} from "@material-ui/core";
import {Link} from "react-router-dom";
import {deleteFeatureAvailabilityRule} from "../services/FeatureAvailabilityRuleService";

interface Props {
    namespace: string;
    feature: string;
    description: string;
    active: boolean;
    id: string;

    updateStateOnDelete(): void;
}

export const RuleRow = (props: Props) => {

    const handleDelete = async () => {
        await deleteFeatureAvailabilityRule(props.id);
        props.updateStateOnDelete();
    };

    return (
        <TableRow>
            <TableCell>{props.namespace}</TableCell>
            <TableCell>{props.feature}</TableCell>
            <TableCell>{props.description}</TableCell>
            <TableCell>{props.active ? 'true' : 'false'}</TableCell>
            <TableCell>
                <Button variant={"contained"}>
                    <Link to={`/edit/${props.id}`}>Edit</Link>
                </Button>
                <Button onClick={handleDelete} variant={"contained"}>
                    Delete
                </Button>
            </TableCell>
        </TableRow>
    );
};

