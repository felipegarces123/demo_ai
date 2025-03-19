# Documentation: `LinkLister` Class

## Overview
The `LinkLister` class provides functionality to extract hyperlinks from a given URL. It includes methods to fetch all links from a webpage and an enhanced version that validates the URL to prevent the use of private IP addresses. This class leverages the `Jsoup` library for HTML parsing and URL connection handling.

---

## Class: `LinkLister`

### Methods

#### 1. `getLinks(String url)`
**Description**:  
Fetches all hyperlinks (`<a>` tags) from the specified URL and returns them as a list of absolute URLs.

**Parameters**:  
| Name | Type   | Description                  |
|------|--------|------------------------------|
| `url` | `String` | The URL of the webpage to parse. |

**Returns**:  
`List<String>`: A list of absolute URLs extracted from the webpage.

**Throws**:  
| Exception | Description |
|-----------|-------------|
| `IOException` | If an error occurs while connecting to the URL or fetching the webpage. |

**Logic**:
1. Connects to the given URL using `Jsoup.connect(url).get()`.
2. Selects all `<a>` elements from the HTML document.
3. Extracts the absolute URL (`href` attribute) from each `<a>` tag.
4. Returns the list of extracted URLs.

---

#### 2. `getLinksV2(String url)`
**Description**:  
An enhanced version of `getLinks` that validates the URL to ensure it does not point to a private IP address. If the URL is valid, it fetches all hyperlinks from the webpage.

**Parameters**:  
| Name | Type   | Description                  |
|------|--------|------------------------------|
| `url` | `String` | The URL of the webpage to parse. |

**Returns**:  
`List<String>`: A list of absolute URLs extracted from the webpage.

**Throws**:  
| Exception | Description |
|-----------|-------------|
| `BadRequest` | If the URL points to a private IP address or if any other error occurs during processing. |

**Logic**:
1. Parses the URL using `java.net.URL` to extract the host.
2. Checks if the host starts with private IP address ranges:
   - `172.*`
   - `192.168.*`
   - `10.*`
3. If the host matches any private IP range, throws a `BadRequest` exception with the message "Use of Private IP".
4. If the host is valid, calls the `getLinks` method to fetch the links.
5. Catches any exceptions during processing and rethrows them as `BadRequest`.

---

## Insights

### Key Features
- **HTML Parsing**: Utilizes the `Jsoup` library to parse HTML documents and extract `<a>` tags.
- **Private IP Validation**: Implements a safeguard in `getLinksV2` to prevent fetching links from private IP addresses, enhancing security.
- **Error Handling**: Provides robust exception handling by wrapping errors in a custom `BadRequest` exception.

### Dependencies
- **`Jsoup` Library**: Required for HTML parsing and URL connection handling.
- **`java.net.URL`**: Used for URL parsing and host extraction.

### Security Considerations
- **Private IP Restriction**: The `getLinksV2` method ensures that private IP addresses are not used, mitigating potential security risks such as accessing internal networks.
- **Exception Wrapping**: All exceptions in `getLinksV2` are wrapped in a `BadRequest` exception, providing a consistent error-handling mechanism.

### Potential Enhancements
- **Customizable IP Validation**: Allow users to configure additional IP ranges or domains to block.
- **Timeout Handling**: Add timeout settings for the `Jsoup.connect` method to handle slow or unresponsive URLs.
- **Logging**: Implement logging for better traceability of errors and processing steps.

---

## File Metadata
| Key         | Value              |
|-------------|--------------------|
| **File Name** | `LinkLister.java` |
