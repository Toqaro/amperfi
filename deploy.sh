#!/bin/bash
cd build/libs/

export SSHPASS=$sftp_passwd
sshpass -e sftp -oBatchMode=no -b - $sftp_user@$sftp_host << !
    cd /amperfi/builds/
    mkdir $TRAVIS_BUILD_NUMBER
    cd $TRAVIS_BUILD_NUMBER
    put *
    bye
!