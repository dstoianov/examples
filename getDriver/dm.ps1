#===========proxy==========
#$proxy = New-Object System.Net.WebProxy("192.168.0.1:80")
#$proxy.useDefaultCredentials = $true
$webclient = New-Object System.Net.WebClient
#$webclient.proxy = $proxy
#==========================

#==========================

#cd C:\Dev\Selenium\

Function Extract-Zip
{
	param([string]$zipfilename, [string] $destination)

	$zipfilename = [string]::Concat($pwd,"\",$zipfilename)
	if(test-path($zipfilename))
	{	
		Write-Host "Start unZip file $zipfilename"
		Write-Host "Save to folder $destination"
		$shellApplication = new-object -com shell.application
		$zipPackage = $shellApplication.NameSpace($zipfilename)
		$destinationFolder = $shellApplication.NameSpace($destination)
		$destinationFolder.CopyHere($zipPackage.Items(), 16)
		Write-Host "==============unZip=DONE============================"
	}
	Write-Host "   "
}


Function unZip($a){
$dest_folder=Get-Item ..
# echo $dest_folder.Fullname
unzip -o *.zip -d $dest_folder
}

Function getFile($url){
	Write-Host "Start download from $url..."
	$name = $url.Substring($url.LastIndexOf("/")+1)
	$file = [string]::Concat($pwd,"\" ,$name)
	$webclient.DownloadFile($url,$file)
	Write-Host "Save to $file"
	Write-Host "=======DONE========="
	Write-Host "   "
}


$url = "http://chromedriver.storage.googleapis.com/LATEST_RELEASE"
getFile($url)
$chrome_ver = $url.Substring($url.LastIndexOf("/")+1)
# echo $chrome_ver

$ver = Get-Content $chrome_ver
echo "===================================================="
echo "The latest version of Chrome Driver is $ver"
echo "===================================================="
mkdir $ver
cd $ver


$list_of_chrome_files = @(
#"http://chromedriver.storage.googleapis.com/$ver/chromedriver_linux32.zip",
#"http://chromedriver.storage.googleapis.com/$ver/chromedriver_linux64.zip",
#"http://chromedriver.storage.googleapis.com/$ver/chromedriver_mac32.zip",
"http://chromedriver.storage.googleapis.com/$ver/chromedriver_win32.zip",
"http://chromedriver.storage.googleapis.com/$ver/notes.txt")


 foreach ($url in $list_of_chrome_files) {
 	getFile($url)
 }
 
$zip_files = Get-ChildItem $pwd -filter "*.zip"
 foreach ($zip in $zip_files) {
	$dest_folder=Get-Item ..
#	echo $dest_folder.Fullname
 	Extract-Zip $zip $dest_folder
 }
 
#unZip("dd") 
#$dest_folder=Get-Item ..
# echo $dest_folder.Fullname
# unzip -o *.zip -d $dest_folder
 
 cd ..
 
 Remove-Item $chrome_ver
 
#=========== END of Chrome Driver ============





getFile("https://selenium.googlecode.com/git/java/CHANGELOG")
$wb_change_log = 'CHANGELOG'
$wb_ver = Get-Content -Path $wb_change_log | Select-Object -First 1
# echo $wb_ver
mkdir $wb_ver
Move-Item $wb_change_log $wb_ver
cd $wb_ver
echo "===================================================="
echo "The latest version of WebDriver is $wb_ver"
echo "===================================================="


$wd_ver_str = $wb_ver.Substring(1,4)
# echo $str_a

$list_of_wd_files = @(
"http://selenium-release.storage.googleapis.com/$wd_ver_str/IEDriverServer_Win32_$wd_ver_str.0.zip")
#"http://selenium-release.storage.googleapis.com/$wd_ver_str/IEDriverServer_x64_$wd_ver_str.0.zip")
#"http://selenium-release.storage.googleapis.com/$wd_ver_str/selenium-server-$wd_ver_str.0.zip")


 foreach ($url in $list_of_wd_files) {
 	getFile($url)
}

 
$zip_files = Get-ChildItem $pwd -filter "*.zip"
 foreach ($zip in $zip_files) {
	$dest_folder=Get-Item ..
#	echo $dest_folder.Fullname
 	Extract-Zip $zip $dest_folder
 }

# $dest_folder = Get-Item ..
# echo $dest_folder.Fullname
# unzip -o *.zip -d $dest_folder
	
 cd..

 
 

 
 
 