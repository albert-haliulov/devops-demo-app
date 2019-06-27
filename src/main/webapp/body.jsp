<div id="message">
    DevOps demo application.
  </div>
  <div id="info">
    <table>
        <tr>
            <td> 
                <table>
                    <tr>
                        <th>Pod:</th>
                    </tr>
                    <tr>
                        <td><% out.println("Name: " + System.getProperty("POD_NAME")); %></td>
                    </tr>
                    <tr>  
                        <td><% out.println("Namespace: " + System.getProperty("POD_NAMESPACE")); %></td>
                    </tr>
                    <tr>  
                        <td><% out.println("IP: " + System.getProperty("POD_IP")); %></td>
                    </tr>
                    <tr>  
                        <td><% out.println("Version: " + System.getProperty("POD_VERSION")); %></td>
                    </tr>
                    <tr>  
                        <td><% out.println("Environment: " + System.getProperty("POD_ENVIRONMENT")); %></td>
                    </tr>
                </table>
            </td>
            <td>
                <table>
                    <tr>
                        <th>Node:</th>
                    </tr>
                    <tr>
                        <td><% out.println("Architecture: " + System.getProperty("os.arch")); %></td>
                    </tr>
                    <tr>
                        <td><% out.println("Platform: " + System.getProperty("os.name")); %></td>
                    </tr>
                    <tr>
                        <td><% out.println("Release: " + System.getProperty("os.version")); %></td>
                    </tr>
                </table>
            </td>
        </tr>
</table>

  </div>
  