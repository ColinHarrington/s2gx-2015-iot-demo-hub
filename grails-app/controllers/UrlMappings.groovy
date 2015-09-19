class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
        "/keypad/auth/$code"(controller: 'keypad', action:'auth')
        "/keypad/authenticate"(controller: 'keypad', action:'auth', method: "POST")

        "/fadecandy/mode/$mode"(controller: 'fadecandy', action: 'mode')
        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
