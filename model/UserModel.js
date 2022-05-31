const Seq = require('sequelize');
module.exports = (sequelize) => {
    const User = sequelize.define(
        "User",
        {
            Username: {type: Seq.STRING, primaryKey: true},
            Email: {type: Seq.STRING, allowNull: false},
            Password: {type: Seq.STRING, allowNull: false},
            FullName: {type: Seq.STRING, allowNull: false},
            ProfilePicture: {type: Seq.STRING, allowNull: true},
        },
        {
            freezeTableName: true,
            timestamp: false
        }
    );
    return User;
}