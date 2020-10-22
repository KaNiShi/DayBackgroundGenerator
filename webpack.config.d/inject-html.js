const HtmlWebpackPlugin = require('html-webpack-plugin');
config.plugins.push(new HtmlWebpackPlugin({
    inject: true,
    template: `${config.output.path}/index.html`
}));