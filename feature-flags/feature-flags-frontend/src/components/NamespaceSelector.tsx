import React from "react";
import {FormControl, InputLabel, MenuItem, Select} from "@material-ui/core";
import {getAllNamespaces} from "../services/FeatureAvailabilityRuleService";
import styles from './Styles.module.css'

interface Props {
    namespace: string;

    handleChange(event: React.ChangeEvent<{ name?: string, value: unknown }>): void;
}

interface State {
    namespaces: string[];
}

export class NamespaceSelector extends React.Component<Props, State> {

    async componentDidMount() {
        const namespaces = await getAllNamespaces();
        this.setState({namespaces});
    }

    render() {
        return (
            <FormControl className={styles.min_input_width}>
                <InputLabel id="namespace-label">Namespace</InputLabel>
                <Select className={styles.min_input_width} labelId="namespace-label"
                        id="namespace-select" value={this.props.namespace} onChange={this.props.handleChange}>
                    {this.renderNamespaceItems()}
                </Select>
            </FormControl>
        );
    }

    private renderNamespaceItems = () => {
        return (this.state?.namespaces || []).map(namespace =>
            <MenuItem key={namespace} value={namespace}>{namespace}</MenuItem>);
    };
}
