# File Organizer CLI Tool

A simple command-line tool that automatically organizes files by categorizing them into subfolders based on file extensions.

## Features

✨ **Smart Organization** - Automatically sorts files into categories (Images, Documents, Music, Videos, etc.)  
🔄 **Recursive Mode** - Organize files in subdirectories with `--recursive`  
📝 **Logging** - Track all operations with `--log` flag  
🛡️ **Error Handling** - Gracefully handles permission errors  

## Project Structure

```
FileOrganizer/
├── src/
│   └── FileOrganizer.java    # Single file - all functionality
├── config/
│   └── extension-mappings.properties
├── README.md
└── .gitignore
```

## Quick Start

### 1. Compile
```bash
cd src
javac FileOrganizer.java
```

### 2. Run
```bash
java FileOrganizer <directory> [--recursive] [--log]
```

## Usage Examples

**Basic organization:**
```bash
java FileOrganizer /Users/shree/Downloads
```

**With logging:**
```bash
java FileOrganizer /Users/shree/Downloads --log
```

**Recursive mode:**
```bash
java FileOrganizer /Users/shree/Downloads --recursive --log
```

**Help:**
```bash
java FileOrganizer --help
```

## File Categories

| Category | Extensions |
|----------|-----------|
| **Images** | jpg, jpeg, png, gif, bmp, svg |
| **Documents** | pdf, doc, docx, txt, xls, xlsx, ppt, pptx |
| **Music** | mp3, wav, flac, aac |
| **Videos** | mp4, avi, mkv, mov |
| **Archives** | zip, rar, 7z, tar, gz |
| **Code** | java, py, js, html, css, cpp, c |
| **Others** | Everything else |

## Example

### Before:
```
Downloads/
├── photo.jpg
├── song.mp3
├── document.pdf
└── script.py
```

### Run:
```bash
java FileOrganizer Downloads --log
```

### After:
```
Downloads/
├── Images/
│   └── photo.jpg
├── Music/
│   └── song.mp3
├── Documents/
│   └── document.pdf
└── Code/
    └── script.py
```

## Log Output
```
[2026-01-31 14:54] photo.jpg → Images
[2026-01-31 14:54] song.mp3 → Music
[2026-01-31 14:54] document.pdf → Documents
[2026-01-31 14:54] script.py → Code
```


## Technical Details

**Data Structures:**
- HashMap for O(1) extension lookup
- File I/O with java.nio.file

**Key Features:**
- Single file implementation (~170 lines)
- No external dependencies
- Clean, readable code

## License

Open source - free for educational use.
=======
# File-Organizer-Auto-Folder-Sorter-CLI-

