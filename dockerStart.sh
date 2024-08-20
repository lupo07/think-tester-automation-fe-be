docker ps>>dockerStart.txt
docker run -d --name test_chrome -p 4444:4444 -e SCREEN_WIDTH=1367 -e SCREEN_HEIGHT=768 -e SCREEN_DEPTH=24 -v /dev/shm:/dev/shm selenium/standalone-chrome:3.141.59-zirconium>dockerStart.txt
exit