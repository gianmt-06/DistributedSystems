module main.gian {
    requires javafx.controls;
    requires javafx.fxml;

    exports main.gian to javafx.graphics;

    opens main.gian.App.Infrastructure.UI.Components.Register to javafx.fxml;
    exports main.gian.App.Infrastructure.UI.Components.Register;

    opens main.gian.App.Infrastructure.UI.Components.MemberCard to javafx.fxml;
    exports main.gian.App.Infrastructure.UI.Components.MemberCard;

    opens main.gian.App.Infrastructure.UI.Components.Chat to javafx.fxml;
    exports main.gian.App.Infrastructure.UI.Components.Chat;
}