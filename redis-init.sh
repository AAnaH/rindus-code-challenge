#!/bin/bash

# Wait for Redis to be available
until redis-cli ping; do
  echo "Waiting for Redis to be available..."
  sleep 1
done

# Load initial data into Redis
redis-cli HSET model "bannerExtSize=0x0" 1.616892819
redis-cli HSET model "bannerExtSize=flexible" 1.578777976
redis-cli HSET model "deviceLanguage=es" 1.134030757
redis-cli HSET model "deviceLanguage=en" 1.111139494
redis-cli HSET model "deviceLanguage=it" 1.06355885
redis-cli HSET model "deviceExtType=tablet" 0.7294739471
redis-cli HSET model "deviceLanguage=ar" 0.6317743188
redis-cli HSET model "deviceLanguage=fr" 0.4418820908
redis-cli HSET model "deviceLanguage=ro" 0.3260918906
redis-cli HSET model "deviceExtBrowser=Chrome" 0.2102317412
redis-cli HSET model "bannerExtSize=800x250" 0.1692596709
redis-cli HSET model "bannerExtSize=300x600" 0.1628927345
redis-cli HSET model "deviceLanguage=ru" -0.0310927102
redis-cli HSET model "deviceExtBrowser=Safari" -0.0360865458
redis-cli HSET model "deviceExtType=mobile" -0.0372569973
redis-cli HSET model "deviceExtBrowser=IE" -0.0406608564
redis-cli HSET model "deviceExtType=desktop" -0.1037572241
redis-cli HSET model "deviceExtBrowser=Firefox" -0.1131013246
redis-cli HSET model "deviceLanguage=de" -0.1935418172
redis-cli HSET model "deviceLanguage=hu" -0.4186695043
redis-cli HSET model "deviceLanguage=tr" -0.4304739397
redis-cli HSET model "bannerExtSize=320x50" -0.4556144288
redis-cli HSET model "bannerExtSize=120x600" -0.5110433495
redis-cli HSET model "bannerExtSize=160x600" -0.5334565661
redis-cli HSET model "deviceLanguage=hr" -0.5559580767
redis-cli HSET model "bannerExtSize=728x90" -0.6115154594
redis-cli HSET model "bannerExtSize=300x250" -0.6282185905
redis-cli HSET model "deviceLanguage=sk" -1.266130497
redis-cli HSET model "deviceLanguage=pl" -1.266865912
redis-cli HSET model "deviceLanguage=cs" -1.640872261
redis-cli HSET model "bias" -6.21176449

echo "Initial data loaded into Redis."