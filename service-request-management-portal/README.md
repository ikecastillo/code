# Auzi Service Request Management Portal

A customized service portal plugin for Jira Service Management with custom Auzi branding.

## Development

This plugin enhances the original Service Request Management Portal with branded styling for Auzi.

### Key Features

- Custom Auzi branding and theme
- Responsive modern UI
- Compatible with JSM 5.12.18
- Customizable color scheme and styling
- No changes to the Active Objects database layer

### Building the Plugin

To build and run the plugin:

```bash
# Build the plugin
atlas-mvn package

# Run in a development JIRA instance
atlas-run
```

### Customization

The plugin uses a CSS-based approach to customization:

- **src/main/resources/css/auzi-custom.css** - Custom styling for Auzi branding
- **src/main/resources/templates/auzi-customer-portal.vm** - Branded template for customer portal
- **src/main/resources/images/auzi-logo.svg** - Auzi logo (placeholder)

### Configuration

After installing the plugin:

1. Navigate to JIRA administration
2. Go to "ServiceDesk" in the top navigation
3. Select "Configuration" to access portal settings

## Technical Notes

- JIRA Version: 5.12.18
- Java Version: 1.8
- Built with the Atlassian Plugin SDK

## License

This project is licensed under the terms specified in the LICENSE file. 