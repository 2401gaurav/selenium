$count = 0

While $count <> 10
$hdl =WinActivate("File Upload")
$hd2 =WinActivate("Open")
Sleep(100)
If $hdl <> 0 Then
ControlFocus("File Upload","","Edit1")
Sleep(700)
ControlSetText("File Upload","","Edit1","C:\Users\a.bashir.jamadar\workspace\AITR2\src\main\resources\InputFiles\suggestion TD.xlsx")
Sleep(700)
ControlClick("File Upload","","Button1")
Exit
EndIf
Sleep(1000)
$count = $count + 1

Sleep(100)
If $hd2 <> 0 Then
ControlFocus("Open","","Edit1")
Sleep(700)
ControlSetText("Open","","Edit1","C:\Users\a.bashir.jamadar\workspace\AITR2\src\main\resources\InputFiles\suggestion TD.xlsx")
Sleep(700)
ControlClick("Open","","Button1")
Exit
EndIf
Sleep(1000)
$count = $count + 1




WEnd