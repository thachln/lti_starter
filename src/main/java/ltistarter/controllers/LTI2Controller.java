/**
 * Copyright 2014 Unicon (R)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ltistarter.controllers;

import ltistarter.lti.LTIRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * This LTI controller should be protected by OAuth 1.0a and is here for cases
 * where we need lti2 specific processing that can't be done under the lti path
 */
@Controller
@RequestMapping("/lti2")
public class LTI2Controller extends BaseController {

    @RequestMapping({"", "/"})
    public String home(HttpServletRequest req, Principal principal, Model model) {
        commonModelPopulate(req, principal, model);
        model.addAttribute("name", "lti2");
        req.getSession().setAttribute("login", "oauth");
        LTIRequest ltiRequest = LTIRequest.getInstance();
        if (ltiRequest != null) {
            model.addAttribute("lti", true);
            model.addAttribute("ltiVersion", ltiRequest.getLtiVersion());
            model.addAttribute("ltiContext", ltiRequest.getLtiContextId());
            model.addAttribute("ltiUser", ltiRequest.getLtiUserDisplayName());
            model.addAttribute("ltiLink", ltiRequest.getLtiLinkId());
        }
        //noinspection SpringMVCViewInspection
        return "home"; // name of the template
    }

}