#!/bin/sh

DEST_FOLDER="./toto" # The folder where to put the downloaded files ie the html pages describing each ingredient 
URL_BASE="http://ndb.nal.usda.gov/ndb/foods/show/" # The url of the USDA database (should not be changed)
FOOD_INDEX=1 # The number of the ingredient to start the download with in the database


#########################################################
#							#			
#	United State Department of Agriculture (USDA)	#
#	DataBase Downloader				#
#							#
#########################################################

### Displays error messages ################################# 
# @params : The message to display before leaving	    #
# @return : 1						    #
die()					
{
	echo Error: $@ 
	exit 1;
}

# Create the folder DEST_FOLDER if it doesn't exist yet
[ -e $DEST_FOLDER ] || mkdir -p $DEST_FOLDER || die "Impossible to create destination folder $DEST_FOLDER"

# Download the first ingredient's html page
wget $URL_BASE$FOOD_INDEX -P $DEST_FOLDER 2> /dev/null || die "Impossible to download food at index $FOOD_INDEX"

# If the download has been successfully done, continue with the next one
while [ $? -eq 0 ]
do
# Displays the name of the ingredient's description downloaded
echo "New food downloaded ($FOOD_INDEX) : `cat $DEST_FOLDER/$FOOD_INDEX | grep nbsp | cut -d ',' -f 2-`"
# If the output file size is equal 0 we try again
[ `ls -lrt $DEST_FOLDER/$FOOD_INDEX | cut -d " " -f 5` -eq 0 ] || FOOD_INDEX=$(($FOOD_INDEX+1))
wget $URL_BASE$FOOD_INDEX -P $DEST_FOLDER 2> /dev/null
done

