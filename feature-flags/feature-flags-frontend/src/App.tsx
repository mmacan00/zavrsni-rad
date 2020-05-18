import React from 'react';
import {BrowserRouter, Link, Route, Switch, useParams} from "react-router-dom";
import {FeatureFlagsTable} from "./components/FeatureFlagsTable";
import {FeatureFlagForm} from "./components/FeatureFlagForm";
import {AppBar, CssBaseline, Toolbar, Typography} from "@material-ui/core";
import styles from "./components/Styles.module.css";
import {Demo} from "./components/Demo";

const App = () => {
    return (
        <BrowserRouter>
            <CssBaseline/>
            <AppBar position="static">
                <Toolbar className={styles.spacing}>
                    <Typography variant="h6" className={styles.margin_padding}>
                        <Link to="/" className={styles.white}>Home</Link>
                    </Typography>
                    <Typography variant="h6" className={styles.margin_padding}>
                        <Link to="/add" className={styles.white}>Add New Feature Flag</Link>
                    </Typography>
                    <Typography variant="h6" className={styles.margin_padding}>
                        <Link to="/demo" className={styles.white}>Demo</Link>
                    </Typography>
                </Toolbar>
            </AppBar>
            <Switch>
                <Route path="/add">
                    <FeatureFlagForm/>
                </Route>
                <Route path="/demo">
                    <Demo/>
                </Route>
                <Route path='/edit/:id'>
                    <EditFeatureFlag/>
                </Route>
                <Route path="/">
                    <FeatureFlagsTable/>
                </Route>
            </Switch>
        </BrowserRouter>
    );
};

const EditFeatureFlag = () => {
    const {id} = useParams();
    return <FeatureFlagForm id={id}/>;
};

export default App;

