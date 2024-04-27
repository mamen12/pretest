import * as React from 'react';

export default class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            active: "login",
            email: "",
            password: "",
            onLogin: props.onLogin,
        };
    };

    onChangeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({[name] : value});
    };

    onSubmitLogin = (e) => {
        this.state.onLogin(e, this.state.email, this.state.password);
    };

    render(){
        return(
            <div className="bg-white">
            <div className="flex items-center justify-center">
                <div className="max-h-auto w-3/4">
                    
                    <form className="w-full" onSubmit={this.onSubmitLogin}>
                        <div className="my-4">
                            <div className="space-y-1.5">
                                <label id="email" name="email" className='font-medium' htmlFor='Email'>Email</label>
                                <input className="w-full px-4 py-2 text-sm rounded-md" placeholder="mail@example.com" name="email" onChange={this.onChangeHandler}/>
                            </div>
                            <div className="space-y-1.5">
                                <label id="password" name="password"  className='font-medium' htmlFor='password'>Password</label>
                                <input className="w-full px-4 py-2 text-sm rounded-md" type="password" placeholder='*********' name="password"  onChange={this.onChangeHandler}/>
                            </div>
                        </div>
                        <button type="submit" className="h-10 w-full items-center justify-center rounded-md bg-purple-700 px-4 py-2 text-sm font-medium transition hover:bg-purple-900 text-white">Login</button>
                    </form>
                </div>
            </div>
            </div>
        );
    };
}