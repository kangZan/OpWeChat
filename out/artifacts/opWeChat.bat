@echo off
rem �˽ű�ʵ��΢�Ŷ࿪����

set "FileName=wechat.exe"
set "script_path=%~f0"
(set/p wechat=<"%script_path%:WX") 2>nul
if "%wechat%" equ "" (echo �״�����,����ɨ��΢��...) else goto :start_wechat

(for %%a in (C D E F) do (
	if exist %%a:\ (
		pushd %%a:\
		for /r %%b in (*%FileName%) do (
			if /i "%%~nxb" equ "%FileName%" (
				
				echo,%%b|findstr /v "%tmp%"&&(
					echo %%b>"%script_path%:WX"
					goto :start_wechat
				)
			)
		)
		popd
	)
))>nul 2>nul
echo;�Բ���,û���ҵ�΢��
pause&exit

:start_wechat
set/p wechat=<"%script_path%:WX"
set /p "num=��Ҫ������΢��:"
for /l %%a in (1,1,%num%) do start "" "%wechat%"