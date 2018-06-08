$title="Seguridad de Windows";
WinWait($title, "", "10");
If WinExists($title, "") Then
WinActivate($title, "");
Send("{ENTER}");
EndIf