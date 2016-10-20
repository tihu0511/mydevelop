var HelloMessage = React.createClass({
    render: function() {
        return <h1>Hello {this.props.name}</h1>;
    }
});


ReactDOM.render(
    <h1>Hello, world!</h1>,
    document.getElementById('container')
);

ReactDOM.render(
    <HelloMessage name="John" />,
    document.getElementById('example')
);