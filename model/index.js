const configDB = require('../config/db');

const Sequel = require('sequelize');

const sequelize = new Sequel(
    configDB.DB,
    configDB.USER,
    configDB.PASSWORD, {
        host: configDB.HOST,
        dialect: configDB.dialect,
        operatorsAlias: false,
    },
    configDB.timezone
);
sequelize.authenticate().then( () => {console.log('connected to DB')}).catch(err => {console.log('Error' + err)});

const db = {};

db.sequelize = sequelize;

db.Ingredient = require('./IngredientModel.js') (sequelize);
db.User = require('./UserModel.js') (sequelize);
db.RefreshToken = require('./RTModel') (sequelize);
db.Notify = require('./NotifyModel') (sequelize);

db.sequelize.sync({force: false}).then(() => {
    console.log('re-sync done')
});

module.exports = db, sequelize;