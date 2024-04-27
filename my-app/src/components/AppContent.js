import * as React from 'react';
import WelcomeContent from './WelcomeContent';
import { request , setAuthHeader } from '../helper/axios.helper';
import LoginForm from './LoginForm';
import DashboardContent from './DashboardContent';
import Header from './Header';

export default class AppContent extends React.Component{

    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "welcome",
            isLoggedIn: false,
            user: null
        }
    };

    login = () => {
        this.setState({componentToShow: "login"})
    };

    logout = () => {
        this.setState({componentToShow: "welcome"})
    }


    onLogin = (e, email, password) => {
        e.preventDefault();
        request("POST", "/auth/login", {
            email: email,
            password: password,
        }).then(
            (response) => {
                setAuthHeader(response.data.data.token);
                this.setState({componentToShow: "dashboard"});
                this.setState({ isLoggedIn: true });
                this.setState({ user: response.data.data });
            }
        ).catch(
            (error) => {
                setAuthHeader(null);
                this.setState({componentToShow: "welcome"});
            }
        );
    };

    render() {
        const { isLoggedIn, user } = this.state;
        return (
            <div>
                <Header isLoggedIn={isLoggedIn}/>
                 {this.state.componentToShow === "welcome" && <WelcomeContent /> }
                
                 {this.state.componentToShow === "welcome" && <LoginForm onLogin={this.onLogin} />}
                {this.state.componentToShow === "dashboard" && <DashboardContent user={user}/> }
                
            </div>
        )
    }
}