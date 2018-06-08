$title="Authentication Required";
WinWait($title, "", "10");
If WinExists($title, "") Then
WinActivate($title, "");
Send("Alta{TAB}");
Send("TennisQa1{ENTER}");
EndIf