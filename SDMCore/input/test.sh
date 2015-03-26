#! /bin/sh

echo 'PROCESS START'
echo 'COPY BLOOMBERG SECUITIES OUT ........................'
cp /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/bloomberg/examples/*.out /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/bloomberg
echo '[OK]'
echo 'WAIT BLOOMBERG SECUITIES OUT PROCESSING .............'
sleep 2m
echo '[OK]'
echo 'COPY BLOOMBERG SECUITIES DIF ........................'
cp /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/bloomberg/examples/*.dif /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/bloomberg
echo '[OK]'
echo 'WAIT BLOOMBERG SECUITIES DIF PROCESSING .............'
sleep 30s
echo '[OK]'
echo 'COPY OFIVAL SECUITIES DIF ...........................'
cp /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/ofival/examples/*.csv /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/securities/ofival
echo '[OK]'
echo 'WAIT OFIVAL SECUITIES PROCESSING ....................'
sleep 30s
echo '[OK]'
echo 'START COAC PROCESSING'
echo 'COPY OFIVAL COACS ...................................'
cp /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/ofival/examples/UE* /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/ofival
echo '[OK]'
echo 'COPY BLOOMBERG COACS ................................'
cp /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/bloomberg/examples/*.cax /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/bloomberg
echo '[OK]'
echo 'COPY SWIFT COACS ....................................'
cp /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/swift/examples/*.mxu /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/swift
echo '[OK]'
echo 'COPY IBERCLEAR COACS ................................'
#cp /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/iberclear/examples/*.dat /home/afarre/Develop/WorkspaceFPM3/CorporateActionsCore/input/coacs/iberclear
echo '[OK]'
echo 'END COAC PROCESSING'
echo 'PROCESS END'
