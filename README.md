# DiscordBot

This is a basic DiscordBot. A lot more functionality needs to be added before it would be viable for use in the wild.


### Configuration

Configuration is done via the .conf file in the resources folder. The default file is called application.conf you can load a custom config your environment by adding `ENV=production` or `ENV=whateveryouwant`. The configuration file name will correspond to what you put in that environment variable.

The current configuration file looks as such:

```hocon
"apiKeys": {
    "github": "githubapikey"
}

"discord": {
    "token": "discordbottoken",
    "owner": "DiscordOwnerID",
}
```