#! /bin/sh

echo 'PROCESS START'
echo 'REMOVE BLOOMBERG SECUITIES OUT ........................'
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/bloomberg/*.dif
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/bloomberg/*.out
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/bloomberg/*.done
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/bloomberg/*.tmp
echo '[OK]'
echo 'REMOVE OFIVAL SECUITIES DIF ...........................'
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/ofival/*.csv
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/ofival/*.done
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/ofival/*.tmp
echo '[OK]'
echo 'START COAC PROCESSING'
echo 'REMOVE OFIVAL COACS ...................................'
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/ofival/*.TXT
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/ofival/*.done
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/ofival/*.tmp
coacs/ofival
echo '[OK]'
echo 'REMOVE BLOOMBERG COACS ................................'
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/bloomberg/*.cax
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/bloomberg/*.done
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/bloomberg/*.tmp
echo '[OK]'
echo 'REMOVE SWIFT COACS ....................................'
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/swift/*.mxu
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/swift/*.done
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/swift/*.tmp
echo '[OK]'
echo 'REMOVE IBERCLEAR COACS ................................'
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/iberclear/*.dat
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/iberclear/*.done
rm /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/iberclear/*.tmp
echo '[OK]'
echo 'END COAC PROCESSING'
echo 'PROCESS END'
