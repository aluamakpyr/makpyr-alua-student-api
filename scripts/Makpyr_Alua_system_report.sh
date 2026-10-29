#!/bin/bash
# Makpyr_Alua_system_report.sh - system monitoring report
# Author: Alua Makpyr, group IT2-2306

STUDENT_NAME="Alua Makpyr"
SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"
LOG_FILE="$SCRIPT_DIR/../logs/Makpyr_Alua_system_report.log"

{
echo "=========================================="
echo " System Report"
echo "=========================================="
echo "Student: $STUDENT_NAME"
echo "Date and time: $(date '+%Y-%m-%d %H:%M:%S')"
echo "Username: $(whoami)"
echo "Operating system: $(grep PRETTY_NAME /etc/os-release | cut -d= -f2 | tr -d '"')"
echo "Kernel version: $(uname -r)"
echo "------------------------------------------"
echo "CPU information:"
echo "CPU cores: $(nproc)"
lscpu | grep -E '^Architecture|^Model name|^CPU\(s\)'
echo "------------------------------------------"
echo "Memory information:"
free -h
echo "------------------------------------------"
echo "Disk usage:"
df -h /
echo "------------------------------------------"
echo "Available disk space on /: $(df -h --output=avail / | tail -n 1 | tr -d ' ')"
echo "=========================================="
} | tee -a "$LOG_FILE"
