const Seq = require('sequelize');
module.exports = (sequelize) => {
    const RefreshToken = sequelize.define(
        "RTStorage",
        {
            RTStore:{type: Seq.STRING, allowNull: false}
        },
        {
            freezeTableName: true,
            timestamp: false
        }
    );
    return RefreshToken;
}