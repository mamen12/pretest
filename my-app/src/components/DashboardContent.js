import * as React from 'react';


export default class AuthContent extends React.Component {
    render() {
          const { user } = this.props;
        return(
            <h1>Hallo, Welcome <span>{user.name}</span></h1>
        );     
    };
}