## PyScriptLoader [v0.0.1]
This is a minecraft plugin for paper/spigot servers, made for running python scripts on your server. Using this plugin you can add features to your server without the hustle of making a plugin.

Example:
```python
# This is how you would broadcast a welcome message when a player joins

def on_player_join(player_name):
    welcome_message = "Welcome to the server, {}!".format(player_name)
    print(welcome_message)
    broadcastMessage(welcome_message)
```

[Documentation](https://github.com/BridgerSilk/PyScriptLoader/blob/main/doc.md)