Powerball links:
https://catalog.data.gov/dataset/lottery-powerball-winning-numbers-beginning-2010  ->  Lottery_Powerball_Winning_Numbers__Beginning_2010.csv
https://www.lottostrategies.com/cgi-bin/jackpot_history/draw_date/101
https://nylottery.ny.gov/


Mega millions links:
https://www.texaslottery.com/export/sites/lottery/Games/Mega_Millions/Winning_Numbers/download.html  ->  megamillions.csv
https://www.megamillions.com/jackpot-history 


How to run
To generate html charts for Powerball with history data and predictions

Step 1.
Change PowerballConstants.REPORTS_FOLDER and PowerballConstants.REPORTS_FOLDER_PATH to local folder.

Step 2.
Run PowerballDownloader.java. It downloads Powerball history file to /org.tauceti.tyche/data/powerball.csv.

Step 3.
Create a /org.tauceti.tyche/data/powerball_predict.txt file and add prediction numbers. Here is an example: 
---------------------------------------------------------------------------------------------------------------------------------------------
# Comments are ignored for parsing. Empty lines are ignored for pasing.

# Title is ignored for parsing.
Draw Date,    Winning Numbers, Powerball

# This is one prediction draw. Add more prediction draws after it and run Powerball.java again.
09/06/2025,   01 27 46 55 64   11,
---------------------------------------------------------------------------------------------------------------------------------------------

Step 4.
Run Powerball.java to generate html charts in the reports folder. 

