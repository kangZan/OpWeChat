@echo off
rem 此脚本实现微信多开功能

set "FileName=wechat.exe"
set "script_path=%~f0"
(set/p wechat=<"%script_path%:WX") 2>nul
if "%wechat%" equ "" (echo 首次运行,正在扫描微信...) else goto :start_wechat

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
echo;对不起,没有找到微信
pause&exit

:start_wechat
set/p wechat=<"%script_path%:WX"
set /p "num=亲要开几个微信:"
for /l %%a in (1,1,%num%) do start "" "%wechat%"