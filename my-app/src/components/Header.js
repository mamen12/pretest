import * as React from 'react';

export default class Header extends React.Component {
    render() {
        const { isLoggedIn } = this.props;
        return(
            <div className='w-full h-20 bg-purple-700 text-white flex rounded-b-lg mb-4'>
                {!isLoggedIn && <div className='w-full items-center text-center justify-center content-center '>
                    <h1 className='font-semibold text-[14px]'>Welcome</h1>
                </div>}
                
                {isLoggedIn && <div className='w-full items-end text-end justify-center content-center mx-4'>
                    <h1 className='font-semibold text-[14px]'>Logout</h1>
                </div> }
            </div>
        );
    };
};