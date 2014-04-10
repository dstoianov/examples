::echo off
@if not exist log mkdir log
@echo Log file for HUB > log\hub.log
java -jar selenium-server-standalone-2.41.0.jar -role hub -hubConfig hub.json -log log\hub.log
