const Seq = require('sequelize');
module.exports = (sequelize) => {
    const Notification = sequelize.define(
        "NotifyAlarm",
        {
            RoutinityName:{type: Seq.STRING, allowNull: false},
            NotifyHour: {type: Seq.TIME, allowNull: false},
            fifteenBefore: {type: Seq.BOOLEAN, allowNull: false},
            thirtyBefore: {type: Seq.BOOLEAN, allowNull: false},
            AlarmDate: {type: Seq.DATEONLY, allowNull: false},
            repeatAlarm: {type: Seq.INTEGER, allowNull: false},
            Username:{type: Seq.STRING, allowNull: false}
        },
        {
            freezeTableName: true,
            timestamp: false
        }
    );
    return Notification;
}