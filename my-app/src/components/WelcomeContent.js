import * as React from 'react';

export default class WelcomeContent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            login: props.login
        };
    };
    render() {
      return (
        <div className="mb-8">
            <h1 className="text-gray-500 font-semibold px-4">Plase Enter your email & password </h1>
        </div>
      ); 
    };
}