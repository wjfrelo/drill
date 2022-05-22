# Obinna Script

This script is designed to route files to targeted s3 buckets based upon file extension type.

# Approach
* Pass file path upon starting script
* Extract filename
* Extract file extension
* Route file based upon extenstion

## Quickstart
Run script with the targetd file path as an argument
```bash
sh obinna.sh /path/to/filename.doc
