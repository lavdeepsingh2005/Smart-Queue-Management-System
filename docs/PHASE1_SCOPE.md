# Phase 1 Scope

The current runnable milestone exposes customer-facing registration, token confirmation, token lookup, queue ordering, and a public queue view. Staff-side operations are intentionally not exposed in this version.

The token status enum includes values that support later milestones. In Phase 1, newly generated tokens enter `WAITING`; there is no staff interface to transition them to `SERVING`, `COMPLETED`, `SKIPPED`, or `CANCELLED`.

Priority is selected in the registration form for prototype demonstration. A deployed system should have an authorised verification process for emergency and assisted categories.
