import React from 'react';
import {AppBar, CssBaseline, Toolbar, Typography} from "@material-ui/core";
import {UsersTable} from './components/UsersTable';
import styles from "./components/Styles.module.css";

function App() {
    return (
        <React.Fragment>
            <CssBaseline/>
            <AppBar position="static">
                <Toolbar className={styles.spacing}>
                    <Typography variant="h6">USER MANAGEMENT</Typography>
                </Toolbar>
            </AppBar>
            <UsersTable/>
        </React.Fragment>
    );
}

export default App;
